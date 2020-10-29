Feature('Answer test');

Scenario("Je reponds à ma question",(I,registerPage,loginPage,questionPage,answerPage) => {
    I.amOnPage("/register")
    registerPage.sendForm('test-answer@test.com','test-answer','https://contacts.heig-vd.ch/picture/87.jpg','test firstname','test lastname','1234')

    I.amOnPage('/login')

    loginPage.sendForm("test-answer@test.com","1234")
    I.amOnPage('/')
    questionPage.sendQuestion('Question pour answer test','Test d\'ajout d\'une reponse');

    I.click('Question pour answer test');

    answerPage.sendForm("Ceci est une reponse");

    I.see("Ceci est une reponse");
});

Scenario("Je reponds une question question",(I,registerPage,loginPage,questionPage,answerPage) => {
    I.amOnPage("/register")
    registerPage.sendForm('test-answer-1@test.com','test-answer-1','https://contacts.heig-vd.ch/picture/87.jpg','test firstname','test lastname','1234')

    I.amOnPage("/register")
    registerPage.sendForm('test-answer-2@test.com','test-answer-2','https://contacts.heig-vd.ch/picture/87.jpg','test firstname','test lastname','1234')

    I.amOnPage('/login')
    loginPage.sendForm("test-answer-1@test.com","1234")
    I.amOnPage('/')
    questionPage.sendQuestion('Question pour answer test 2','Test d\'ajout d\'une reponse');


    I.amOnPage('/login')
    loginPage.sendForm("test-answer-2@test.com","1234")
    I.amOnPage('/')

    I.click('Question pour answer test 2');

    answerPage.sendForm("Ceci est une reponse");

    I.see("Ceci est une reponse");
});
