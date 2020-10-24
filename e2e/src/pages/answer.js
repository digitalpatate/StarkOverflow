const {I} = inject();

module.exports = {

    fields: {
        content: 'textarea#answerContent',
    },
    submitButton: 'input#submitAnswer',

    sendForm(content) {
        I.fillField(this.fields.content, content);
        I.click(this.submitButton);
    },

}
