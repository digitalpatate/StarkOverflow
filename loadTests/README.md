# How to run load tests

## Requirements

- Maven: 3.6.3
- OpenJDK: 11
- docker-compose 1.25.0
- JMeter 5.3

## Load testing setup

1) Make sure the database is up and empty by executing in ```/docker/database``` the command : 

```bash
$ docker-compose down --v && docker-compose  up
```

2)  Then make sure that the application server is started. you can run it with Maven issuing the command at the root of the folder :

```bash
$ mvn liberty:stop liberty:clean liberty:dev
```

3) You should have now StarkOverflow up and running at ```localhost:9080``` with an empty database.
4) you can now open and run tests ```/loadTests/[testFileName].jmx``` with *JMeter*

## Tests

### Question vote test
To be sure vote are correctly taken in account under high load, we made a test that
test it for question votes.

What do we do in this test ?

![Question vote in JMeter](./img/questionVote.png)

First a setUp thread group of 20 units create a question then the question id is retrieved
with a regex in a questionId variable. To have this variable available in other thread
groups we use an Assertion BeanShell.
see : [JMeter pass variables between thread groups](https://devqa.io/jmeter-pass-variables-between-thread-groups/)

After that, the vote Thread group register a user, log in this user and vote on this question.
It does that in a loop for 10 iterations.

Lastly, the tearDown thread group extract the number of vote of the question and assert that the value is 200 (20 units * 10 iterations).

## Results
Our results show that question votes are taken in account even under high load.