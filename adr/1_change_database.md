# Changed Database to RDBMS(H2) from NoSql(MongoDB)

## Status

Accepted

## Context

After having a quick glance at the problem statement, I thought of using NoSql like MongoDB as I expected the customer
domain may have non structural data like description or summary, something like his customer credit record or the remark
on his/her profile by the loan approval officer etc. We foresee the search API too, hence a NoSql database could have been
helpful for natual search. However, when I looked at sample response, it expects ID to be in integer form. Well, with MongoDB too,
we can have integer resource identifier. It will require some JavaScript functions at the DB server or a lot of additional
code to come up with next sequence. Considering the trade-offs and YAGNI, I thought of changing DB to relational.

## Decision

Changed the database to relational DB - H2 as the resource identifier id integer. I chose H2 as to serve embedded database
so that there is no additional DB set up required for the application to be running. Considering the current requirement,
this serves the best.

## Consequences

We might need to introduce the search engines like Elastic, Solr etc. for natual search API in future.
