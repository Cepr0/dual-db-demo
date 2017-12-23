Spring Boot project that demonstrates an access to two databases (PostgreSQL) with using one JTA transaction manager (Bitronix)

To make it work properly it's necessary to set PostgreSQL server property `max_prepared_transactions` to nonzero value (in postgresql.conf).  