const {I} = inject();

module.exports = {

    fields: {
        email: 'input#email[type=email]',
        username: 'input#username[type=text]',
        pictureUrl: 'input#profilePicture[type=url]',
        firstname: 'input#firstname[type=text]',
        lastname: 'input#lastname[type=text]',
        password: 'input#password[type=password]'
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
