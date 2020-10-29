Feature('Comment test');

Scenario("Je comment ma question",(I,registerPage,loginPage,questionPage,question_commentPage) => {
    I.amOnPage("/register")
    registerPage.sendForm('test-comment-question@test.com','test-comment-question','https://contacts.heig-vd.ch/picture/87.jpg','test firstname','test lastname','1234')

    I.amOnPage('/login')
    loginPage.sendForm("test-comment-question@test.com","1234")

    I.amOnPage('/')
    questionPage.sendQuestion('Question pour question-comment test','Test d\'ajout d\'une reponse');

    I.click('Question pour question-comment test');

    I.click("i#btnQuestionComment")
    question_commentPage.sendForm("Ceci est un commentaire")

    I.see("Ceci est un commentaire");

});

Scenario("Je comment une question",(I,registerPage,loginPage,questionPage,question_commentPage) => {
    I.amOnPage("/register")
    registerPage.sendForm('test-comment-question-1@test.com','test-comment-question-1','https://contacts.heig-vd.ch/picture/87.jpg','test firstname','test lastname','1234')

    I.amOnPage("/register")
    registerPage.sendForm('test-comment-question-2@test.com','test-comment-question-2','https://contacts.heig-vd.ch/picture/87.jpg','test firstname','test lastname','1234')

    I.amOnPage('/login')
    loginPage.sendForm("test-comment-question-1@test.com","1234")
    I.amOnPage('/')
    questionPage.sendQuestion('Question pour question-comment test 2','Test d\'ajout d\'une reponse');


    I.amOnPage('/login')
    loginPage.sendForm("test-comment-question-2@test.com","1234")
    I.amOnPage('/')

    I.click('Question pour question-comment test 2');

    I.click("i#btnQuestionComment")

    question_commentPage.sendForm("Ceci est un commentaire 2")

    I.see("Ceci est un commentaire 2");
});
