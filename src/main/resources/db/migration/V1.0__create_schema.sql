Drop Table IF EXISTS country_clearing_cost;
CREATE TABLE country_clearing_cost (
      country_code varchar(2) not null,
      cost decimal(19,2),
      primary key (country_code)
);