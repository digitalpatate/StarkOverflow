Feature('Test header content');

Scenario('Il y a le nom du site qui ammène à la page d\'accueil', (I, loginPage) => {
    I.amOnPage('/');
    I.clickLink(
        locate('a').withChild('h1').withText('StarkØverflØw')
    );
    I.amOnPage('/login');
})

Scenario('Il y a un lien vers le login dans le header', (I, loginPage) => {
    I.amOnPage('/');
    I.clickLink(
        locate('a').withText('login')
    );
    I.amOnPage('/');
})