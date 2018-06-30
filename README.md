Website for findeasily.net
============================

[![CircleCI](https://circleci.com/gh/zhouhao/findeasily.net/tree/master.svg?style=svg&circle-token=afb24021c44ad75ddad34fd29ac8ed311887763c)](https://circleci.com/gh/zhouhao/findeasily.net/tree/master)

### The Wish
1. landlord can post their resources into this website easily(?How easily?)
2. tenant can search house of rent by map, which is more informative

### Quick start
1. Please make sure you have `Java8` and `git` installed
2. `git clone https://github.com/zhouhao/findeasily.net.git`
3. Create you configuration file(*optional step*, e.g. your file is `application-sample.properties`) in [`resources`](https://github.com/zhouhao/findeasily.net/tree/docs/src/main/resources) folder(use [`application-dev.properties`](application-dev.properties) as example)
4. `SPRING_PROFILES_ACTIVE=sample mvn spring-boot:run`
5. Visit [http://localhost:8080/](http://localhost:8080/) if everything is OK


### Note:
1. I recommend [**Intellij**](https://www.jetbrains.com/idea/download/#section=mac) as IDE(either Ultimate or Community).(Eclipse is OK, but as I know, Lombok plugin does not work well in Eclipse)
2. You do not need to patch DB schemas manually, as flyway can do it automatically. (Attention: All DB change should have its SQL file in [db/migration](https://github.com/zhouhao/findeasily.net/tree/docs/src/main/resources/db/migration) folder)


### Tech Details
// todo