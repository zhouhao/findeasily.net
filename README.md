Website for findeasily.net
============================

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/286aea9e33f04753b8c6b458001fa4ac)](https://app.codacy.com/app/hzhou.me/findeasily.net?utm_source=github.com&utm_medium=referral&utm_content=findeasily/findeasily.net&utm_campaign=Badge_Grade_Dashboard)
[![CircleCI](https://circleci.com/gh/findeasily/findeasily.net.svg?style=svg)](https://circleci.com/gh/findeasily/findeasily.net)
[![Known Vulnerabilities](https://snyk.io/test/github/findeasily/findeasily.net/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/findeasily/findeasily.net?targetFile=pom.xml)
[![codebeat badge](https://codebeat.co/badges/85b75467-0afb-4436-beaf-f8db43517f81)](https://codebeat.co/projects/github-com-findeasily-findeasily-net-master)
[![BCH compliance](https://bettercodehub.com/edge/badge/findeasily/findeasily.net?branch=master)](https://bettercodehub.com/)

### Note
**I use some commercial resources in my project, for which I have no right to sell or distribute for free, so please purchase your license before using it in your personal project. Thank you!**
* Front-end UI template: https://themeforest.net/item/citybook-directory-listing-template/21360333
* Email UI template: https://themeforest.net/item/commerce-responsive-transactional-email-html-templates/13527744

### The Wish
1. landlord can post their resources into this website easily(?How easily?)
2. tenant can search house of rent by map, which is more informative

### Quick start
1. Please make sure you have `Java11` and `git` installed
2. `git clone https://github.com/findeasily/findeasily.net.git`
3. Create a writable folder for file uploading with following commands:
    ```bash
    sudo mkdir -p /var/findeasily/resources  # create folder
    sudo chmod 777 /var/findeasily/resources  # make folder writable
    ```
   Or you can change the folder by updating this [configuration](./src/main/resources/application.properties#L34).
4. You may need to configure Intellij for lombok support, please see this [document](./docs/Intellij-lombok-setup.md) for more details.
5. Follow [this](./script/README.md) to start mysql & redis with docker
6. <del>You can create your own [mailgun](https://www.mailgun.com/) account, and update Email sending account</del> [here](./src/main/resources/application-dev.properties) (**NOTE: Just use your gmail here**)
7. `SPRING_PROFILES_ACTIVE=dev ./mvnw spring-boot:run`
8. Visit [http://localhost:8080/](http://localhost:8080/), if everything is OK, you can see the home page
9. If you want to make google map work in dev mode, you can follow [this doc](./docs/how-to-get-google-map-api-key.md) to set up Google Map API key.
9. Please follow [this doc](./docs/recaptcha-setup.md) to set up Google recaptcha.

### Note:
1. I recommend [**Intellij**](https://www.jetbrains.com/idea/download/) as IDE(either Ultimate or Community).(Eclipse is OK, but as I know, Lombok plugin does not work well in Eclipse)
2. You do not need to patch DB schemas manually, as flyway can do it automatically. (Attention: All DB change should have its SQL file in [db/migration](./src/main/resources/db/migration) folder)
3. For people who just want to read the source code, you can install [Octotree plugin for Chrome](https://chrome.google.com/webstore/detail/octotree/bkhaagjahfmjljalopjnoealnfndnagc?hl=en-US)

### Tech Details
1. **Framework**: Please see the project tags in the page top
2. **Infrastructure**: MySQL, Redis for session, Nginx(https with [Certbot](https://certbot.eff.org/)), executable Jar with SystemCtl
3. ... more ...

### Resources 
(*Note*: You need to purchase licenses before using these 2 resources into your personal project)
1. The UI template I bought: [Official Demo Link with Images](http://citybook.kwst.net/)
2. The Email template I bought: [Lil Commerce](http://notification-emails.com/transactional-email-templates) 
3. UI: how freemarker works in Spring to make java developer happy.
[Freemarker level up](https://freemarker.apache.org/docs/dgui_quickstart_basics.html)

### Roadmap
- [x] Project configuration setup
- [x] User registration with email activation
- [x] User login
- [x] Password reset with Email
- [x] User profile page
    - [x] Update profile picture
- [ ] User can create/edit/delete listing info
   - [ ] Create
   - [ ] Edit/Update
   - [ ] Delete
   - [ ] View
- [ ] Enable listing display in home page
- [x] Add contact page


### Discussion
Feel free to open new issues, or find me via [Slack](https://join.slack.com/t/findeasily/shared_invite/enQtNzExMDA0OTM5NDkwLTQ1MDIxYTUxYThhOGMxOTE3MDM4NTE3M2FhMzUyMzU0NWI0YzMyNmRjMmEyZTZjNjg3M2Q4YTE4ODQ1NjQwODA)
