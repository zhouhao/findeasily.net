Website for findeasily.net
============================


[![CircleCI](https://circleci.com/gh/zhouhao/findeasily.net/tree/master.svg?style=svg&circle-token=afb24021c44ad75ddad34fd29ac8ed311887763c)](https://circleci.com/gh/zhouhao/findeasily.net/tree/master)

### Note
** I use the UI template here: https://themeforest.net/item/citybook-directory-listing-template/21360333, please purchase your license before using it in your personal project. **

### The Wish
1. landlord can post their resources into this website easily(?How easily?)
2. tenant can search house of rent by map, which is more informative

### Quick start
1. Please make sure you have `Java8` and `git` installed
2. `git clone https://github.com/zhouhao/findeasily.net.git`
3. *You can skip this step now*: Create you configuration file(*optional step*, e.g. your file is `application-sample.properties`) in [`resources`](https://github.com/zhouhao/findeasily.net/tree/master/src/main/resources) folder(use [`application-dev.properties`](https://github.com/zhouhao/findeasily.net/blob/master/src/main/resources/application-dev.properties) as example)
4. `SPRING_PROFILES_ACTIVE=dev ./mvnw spring-boot:run`
5. Visit [http://localhost:8080/](http://localhost:8080/), if everything is OK, you can see the home page

### Note:
1. I recommend [**Intellij**](https://www.jetbrains.com/idea/download/#section=mac) as IDE(either Ultimate or Community).(Eclipse is OK, but as I know, Lombok plugin does not work well in Eclipse)
2. You do not need to patch DB schemas manually, as flyway can do it automatically. (Attention: All DB change should have its SQL file in [db/migration](https://github.com/zhouhao/findeasily.net/tree/master/src/main/resources/db/migration) folder)
3. For people who just want to read the source code, you can install [Octotree plugin for Chrome](https://chrome.google.com/webstore/detail/octotree/bkhaagjahfmjljalopjnoealnfndnagc?hl=en-US)

### Tech Details
1. **Framework**: Please see the project tags in the page top
2. **Infrastructure**: MySQL, Redis for session, Nginx(https with [Certbot](https://certbot.eff.org/)), executable Jar with SystemCtl
3. ... more ...

### How to Contribute
1. Create your own branch (*Note: name your branch based on the feature*)
2. Commit your changes in your branch
3. Open pull request
4. Merge after CI job (code review, if required)
5. Delete your branch if you want

### Resources 
(*Note*: 对于那些有版权的模板，请不要用于自己的公开项目里面 - 当然自己买了版权除外)
1. The UI template I bought: [Official Demo Link with Images](http://citybook.kwst.net/)


### Roadmap
- [x] Project configuration setup
- [x] User registration with email activation
- [x] User login
- [x] Password reset with Email
- [ ] User profile page
- [ ] User can create/edit/delete listing info
- [ ] enable listing display in home page
