# SER322GameDatabase
This repository hosts the Database implementation for our SER322</br>
Game Wiki Database.</br>
</br>
There are 4 scripts that have to do with initializing/maintaining the db.</br>
These are:</br>
  CreateDB.sql</br>
    This script creates the database. It should be run from postgres user
  DropDB.sql</br>
    This script drops the database. It should be run after DropTableAndSchema.sql from
    the postgres user.</br>
  DropTableAndSchema.sql</br>
    This script drops the table and the schema. It should be run from the postgres
    user with a connection to the SER322_DB database (created by CreateDB.sql)</br>
  LoadDB.sql</br>
    This script loads the database which includes creating the tables and
    providing example inserts to run queries against. It should be run as the
    postgres user with a connection to the SER322_DB.</br>
</br>
There are a number of SQL queries under Queries which can be run as examples.</br>
