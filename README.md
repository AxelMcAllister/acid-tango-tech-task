# Acid Tango Technical Task

### Implementing a DDD back-end solution with Spring Boot

Here I present my solution to the code challenge proposed by Acid Tango, for which you can read its **Product Requirement
Document** [here](https://github.com/AxelMcAllister/acid-tango-tech-task/blob/master/ITX%20Caso%20Pra%CC%81ctico%20Backend%20Tools%202023.pdf).

## Previous analysis

From these requirements, I extracted some of the core concepts, like **product**, **sales units**, **stocks**, **sizes**, **sorting criteria**, and
**relative weights**, and inferred others, like **product variants**, that will become the **entities** and **value objects** of the **Domain Layer**.
And from these concepts, I also established **store** as the only **Bounded Context**.

A real store would have several **Aggregates** like **orders**, **customers**, etc, but for the sake of simplicity, and as the requirements do not
say anything about other **Aggregates**, I will stick to just one: **products**.
Having only one **Aggregate** also means that I don't need **Domain Events** to communicate between **Aggregates**.

## Implementation and design choices

### Incremental Ids

The requirements state that products have incremental Ids, which are not optimal from a DDD standpoint, because this forces me to either
query the database to get the next available Id, or let the database generate it, coupling de **Domain Layer** to a specific implementation of the
**Infrastructure Layer** in the process. For this reason, I decided to use **ObjectId**s instead (being **UUID**s also a good option).

### MongoDB infrastructure

For data storage, I assessed the possibility of running a [MongoDB Docker image](https://hub.docker.com/_/mongo), but I ended up using
an [Atlas MongoDB](https://www.mongodb.com/) free-tier database instance, which makes it easier to run the application.

## Testing

For integration testing with **Mongo**, I considered using an **Embedded MongoDB**, like the ones provided
by [Flapdoodle](https://mvnrepository.com/artifact/de.flapdoodle.embed/de.flapdoodle.embed.mongo/4.7.0) or [mongoUnit](https://mongounit.org/), but
finally, I decided to create a test database in the same **Atlas instance** as the _development_ one.

