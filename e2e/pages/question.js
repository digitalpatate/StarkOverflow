const {I} = inject();

module.exports = {

    fields: {
        title: 'input#questionTitle[type=text]',
        content: 'textarea#questionContent'
    },
    submitButton: 'button[type=submit]',

    sendQuestion(title, content) {
        I.fillField(this.fields.title, title);
        I.fillField(this.fields.content, content);
        I.click(this.submitButton);
    },

}