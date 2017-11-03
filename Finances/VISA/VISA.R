library(pdftools)
library(readtext)
library(qdap)
library(stringr)
library(reshape2)
library(dplyr)
library(ggplot2)

# NOTE: this program works for spendings in invoice. Spendings at the top 
# which are not included.

MY_SPENDINGS <- "NAME"


# READ FILE INTO A DATA FRAME ---------------------------------------------

readtxt <- readtext("VISA/08_2017_Visa.pdf")
txt <- readtxt$text

# strip text to get table 
text.split <- regmatches(txt, regexpr("SALDO INICIAL", txt), invert = TRUE)
text.split <- text.split[[1]][2]


# strip SALDO INICIAL amount
# Find first new line
text.split <- regmatches(text.split, regexpr("\n", text.split), invert = TRUE)[[1]][2]
# trim all white space from the left
text.split <- str_trim(text.split, side = "left")

# convert to data frame, each row is a purchase
invoice.df <- as.data.frame(strsplit(text.split, "\n"), 
                            row.names = NULL, 
                            col.names = NULL, 
                            fix.empty.names = F,
                            stringsAsFactors = FALSE)


# View(invoice.df)
# extract MY_SPENDINGS ----------------------------------------------------

names(invoice.df) <- "spendings"
start <- grep(MY_SPENDINGS, invoice.df$spendings) + 1
CONSUMO_DEL_MES.index <- grep("SALDO DIFERIDO", 
                              invoice.df$spendings[start:nrow(invoice.df)])[1] + start - 2
CONSUMO_DEL_MES <-invoice.df[CONSUMO_DEL_MES.index, ]  # to verify total spendings 
CONSUMO_DEL_MES <- word(CONSUMO_DEL_MES, -1) %>% {gsub(",", "", .)}
end <- CONSUMO_DEL_MES.index - 1

my_spendings.char <- invoice.df[start:end,]



# Convert my_spendings into data frame ------------------------------------
# split rows into columns

my_spendings.char <- gsub("\\s+"," ", my_spendings.char) # remove extra whitespaces
my_spendings.char <- str_trim(my_spendings.char, side = "left") #remove whitespaces from the left

dates <- word(my_spendings.char, 1) # extract first word

nwords <- function(string, pseudo=F){
  ifelse( pseudo, 
          pattern <- "\\S+", 
          pattern <- "[[:alpha:]]+" 
  )
  str_count(string, pattern)
}

# extract full description
description <- word(my_spendings.char,3, nwords(my_spendings.char, pseudo = T) - 2) 
amount <- word(my_spendings.char, -1) # extract last word
amount <-  gsub(",", "", amount)

# remove strigs about new page
indices <- sapply(dates,  function(x) nchar(x) != 10) %>% which() 

if(!length(indices) == 0){
  dates <- dates[-indices]
  description <- description[-indices]
  amount <- amount[-indices]}

# combine into data frame
my_spendings.df <- data.frame("Dates" = dates, 
                              "Description" = description, 
                              "Amount" = amount, stringsAsFactors = FALSE)

# View(my_spendings.df)

# Classify Spendings ------------------------------------------------------
# Generalize purchases
# for example same brand, different locations 
my_spendings.df[grep("UBER", my_spendings.df$Description), 2] <- "UBER"
my_spendings.df[grep("SUPERMAXI", my_spendings.df$Description), 2] <- "SUPERMAXI"
my_spendings.df[grep("MEGAMAXI", my_spendings.df$Description), 2] <- "MEGAMAXI"
my_spendings.df[grep("JUAN V", my_spendings.df$Description), 2] <- "JUAN VALDEZ"
my_spendings.df[grep("PnP", my_spendings.df$Description), 2] <- "PICK N PAY"
my_spendings.df[grep("VIDA E CAFFE", my_spendings.df$Description), 2] <- "VIDA E CAFFE"

# Add spendings by categories

x <- melt(my_spendings.df[2:3], id.vars = "Description") # decompose by description
x$Amount <- ave(x$Description, x$Description, FUN = seq_along) # number Amounts
# transpose duplicates by column value
my_spendings.sum.df <- dcast(x, Description ~ Amount, value.var = "value") 

# convert to numeric
my_spendings.sum.df[, 2:ncol(my_spendings.sum.df)] <- 
  apply(as.data.frame(my_spendings.sum.df[, 2:ncol(my_spendings.sum.df)]), 2, function(x) as.numeric(as.character(x)))

# build data frame with rowsums
my_spendings.sum.df  <- data.frame(Description = my_spendings.sum.df$Description,
                                   stringsAsFactors = FALSE) %>%
  mutate(TOTAL = rowSums(my_spendings.sum.df[2:ncol(my_spendings.sum.df)], na.rm = TRUE))

# View(my_spendings.sum.df)

# Visualize ---------------------------------------------------------------

TITLE <- paste("Informe Estado de Cuenta", MY_SPENDINGS, "\n", 
               "Consumo Total: ", CONSUMO_DEL_MES, " USD")

ggplot(my_spendings.sum.df, aes(reorder(Description, -TOTAL),  TOTAL)) + 
  geom_bar(stat = "identity", aes(fill = Description), color = "white") + 
  guides(fill=FALSE) +
  theme(axis.text.x = element_text(angle = 90, hjust = 1), plot.title = element_text(hjust = 0.5)) + 
  labs(title = TITLE, x = "Descripcion", y = "Total") +
  geom_text(aes(label= TOTAL), position=position_dodge(width=0.9), vjust=-0.25, size = 2)