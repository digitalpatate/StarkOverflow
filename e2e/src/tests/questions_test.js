Feature('Test questions');

Scenario('Il y a un champ titre', (I, questionPage) => {
    I.amOnPage('/');
    I.seeElement(questionPage.fields.title);
});

Scenario('Le champ titre est obligatoire', (I, questionPage) => {
    I.amOnPage('/');
    I.seeElement(
        locate(questionPage.fields.title)
            .withAttr({required: 'true'})
    );
});

Scenario('Il y a un champ contenu', (I, questionPage) => {
    I.amOnPage('/');
    I.seeElement(questionPage.fields.content);
});

Scenario('Le champ contenu est obligatoire', (I, questionPage) => {
    I.amOnPage('/');
    I.seeElement(
        locate(questionPage.fields.content)
            .withAttr({required: 'true'})
    );
});

Scenario('Il est possible de poster une question', async(I, questionPage,registerPage,loginPage) => {
    I.amOnPage("/register")
    registerPage.sendForm('test2@test.com','showman','https://contacts.heig-vd.ch/picture/87.jpg','test firstname','test lastname','1234')
    //await new Promise(r => setTimeout(r, 5000));
    I.amOnPage('/login');
    await loginPage.sendForm('test2@test.com','1234');
    //await new Promise(r => setTimeout(r, 5000));
    I.amOnPage('/');


    questionPage.sendQuestion('Ceci est une question de test','Et ceci est son contenu !');
    I.seeElement(
        locate('.question')
            .withDescendant('.question-title')
            .withText('Ceci est une question de test')
    );
});

Scenario('Un utilisateur doit s\'authentifier pour pouvoir poser une question', (I, questionPage) => {
    I.amOnPage('/');
    questionPage.sendQuestion('Ceci est une question de test','Et ceci est son contenu !');
    I.amOnPage('/login');
});

