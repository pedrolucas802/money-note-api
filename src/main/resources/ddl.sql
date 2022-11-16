CREATE TABLE income (
                        idIncome  SERIAL PRIMARY KEY,
                        description VARCHAR(100),
                        value DECIMAL,
                        icomeDate TIMESTAMP NOT NULL DEFAULT NOW(),
                        incomeConst BOOLEAN

);


CREATE TABLE insvestment (
                             idInvestment SERIAL PRIMARY KEY,
                             description VARCHAR(100),
                             value DECIMAL(50),
                             investmentDate	TIMESTAMP NOT NULL DEFAULT NOW()
);


CREATE TABLE category (
                          idCategory SERIAL PRIMARY KEY,
                          description VARCHAR(100)
);

CREATE TABLE purchase (
                          idPurchase SERIAL PRIMARY KEY,
                          description VARCHAR(100),
                          value DECIMAL(50),
                          purchaseDate TIMESTAMP NOT NULL DEFAULT NOW(),
                          purchaseConst BOOLEAN,
                          purchaseCategory integer REFERENCES category (idCategory)

);