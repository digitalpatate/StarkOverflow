const { setHeadlessWhen } = require('@codeceptjs/configure');

// turn on headless mode when running with HEADLESS=true environment variable
// export HEADLESS=true && npx codeceptjs run
setHeadlessWhen(process.env.HEADLESS);

exports.config = {
  tests: './tests/*_test.js',
  output: './output',
  helpers: {
    Puppeteer: {
      url: 'http://localhost:9080',
      show: true,
      windowSize: '1200x900',
      waitForNavigation: [ "domcontentloaded", "networkidle2" ],
      waitForAction: 500
    }
  },
  include: {
    I: './steps_file.js',
    loginPage: "./pages/login.js",
    registerPage: "./pages/register.js",

    questionPage: "./pages/question.js",
    answerPage: "./pages/answer.js"

  },
  bootstrap: null,
  mocha: {},
  name: 'e2e',
  plugins: {
    retryFailedStep: {
      enabled: true
    },
    screenshotOnFail: {
      enabled: true
    }
  }
}
