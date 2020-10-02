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

Scenario('Il est possible de poster une question', (I, questionPage) => {
    // FIXME: s'authentifier avant de poser la question
    I.amOnPage('/');
    questionPage.sendQuestion('Ceci est une question de test','Et ceci est son contenu !');
    I.seeElement(
        locate('.question-list')
            .withDescendant('.question-title')
            .withText('Ceci est une question de test')
    );
});

Scenario('Un utilisateur doit s\'authentifier pour pouvoir poser une question', (I, questionPage) => {
    I.amOnPage('/');
    questionPage.sendQuestion('Ceci est une question de test','Et ceci est son contenu !');
    I.amOnPage('/login');
});