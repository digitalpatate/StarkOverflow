Feature('Comment test');

Scenario("Je comment ma reponse",(I,registerPage,loginPage,questionPage,answerPage,answer_commentPage) => {
    I.amOnPage("/register")
    registerPage.sendForm('test-comment-answer@test.com','test-comment-answer','https://contacts.heig-vd.ch/picture/87.jpg','test firstname','test lastname','1234')

    I.amOnPage('/login')

    loginPage.sendForm("test-comment-answer@test.com","1234")
    I.amOnPage('/')
    questionPage.sendQuestion('Question pour answer-comment test','Test d\'ajout d\'une reponse');

    I.click('Question pour answer-comment test');

    answerPage.sendForm("Ceci est une reponse");

    I.click("i#btnAnswerComment")

    answer_commentPage.sendForm("Ceci est un commentaire")

    I.see("Ceci est un commentaire");
});

Scenario("Je comment une reéponse",(I,registerPage,loginPage,questionPage,answerPage,answer_commentPage) => {
    I.amOnPage("/register")
    registerPage.sendForm('test-comment-answer-1@test.com','test-comment-answer-1','https://contacts.heig-vd.ch/picture/87.jpg','test firstname','test lastname','1234')

    I.amOnPage("/register")
    registerPage.sendForm('test-comment-answer-2@test.com','test-comment-answer-2','https://contacts.heig-vd.ch/picture/87.jpg','test firstname','test lastname','1234')

    I.amOnPage('/login')
    loginPage.sendForm("test-comment-answer-1@test.com","1234")
    I.amOnPage('/')
    questionPage.sendQuestion('Question pour answer-comment test 2','Test d\'ajout d\'une reponse');


    I.amOnPage('/login')
    loginPage.sendForm("test-comment-answer-2@test.com","1234")
    I.amOnPage('/')

    I.click('Question pour answer-comment test 2');

    answerPage.sendForm("Ceci est une reponse");

    I.click("i#btnAnswerComment")

    answer_commentPage.sendForm("Ceci est un commentaire 2")

    I.see("Ceci est un commentaire 2");

});
