const {I} = inject();

module.exports = {

    fields: {
        email: 'input[type=text]',
        username: 'input[type=text]',
        pictureUrl: 'input[type=url]',
        firstname: 'input[type=text]',
        lastname: 'input[type=text]',
        password: 'input[type=password]'
    },
    submitButton: 'input[type=submit]',

    sendForm(email, username,pictureURL, firstname, lastname, password) {
        I.fillField(this.fields.email, email);
        I.fillField(this.fields.username, username);
        I.fillField(this.fields.pictureUrl, pictureURL);
        I.fillField(this.fields.firstname, firstname);
        I.fillField(this.fields.lastname, lastname);
        I.fillField(this.fields.password, password);
        I.click(this.submitButton);
    },

}
