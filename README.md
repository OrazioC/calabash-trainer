CALABASH-TRAINER

Releasing a mobile app without testing it properly divides users who demand quality working apps and what developers are able to provide.
To solve this problem, we propose adopting a continuous delivery approach, by having a test suite of automated functional and unit tests.
Developers mostly blame the time constraints and the high learning curve for testing technologies for not releasing a properly tested app.
The calabash-trainer app focuses on drastically reducing the learning curve for developers and QA regarding functional tests using Calabash
giving a simple but concise starting point.



HOW TO START USING CALABASH-ANDROID FOR YOUR ANDROID APP

PREREQUISITES

1. Ruby version >= 1.9.2
2. Bundler gem -> gem install bundler
3. Gemfile in the root folder of your app
   Gemfile content:
     gem "calabash-android", "0.5" (0.5 is the latest calabash-android version at the moment)
4. Resolve the dependencies defined in the Gemfile, run -> bundle install



SET UP STEPS

1. In your app root folder run

   -> bundle exec calabash-android gen

   It will create a Cucumber skeleton in the current folder

```
   features
   |_support
   | |_app_installation_hooks.rb
   | |_app_life_cycle_hooks.rb
   | |_env.rb
   | |_hooks.rb
   |_step_definitions
   | |_calabash_steps.rb
   |_my_first.feature

```

2. Add the following permission to the AndroidManifest.xml

    <uses-permission android:name="android.permission.INTERNET" />

    This is needed to interact with the Instrumentation Test Server which uses HTTP connections

```
     ___________________________                   ___________________________
    |   Developer computer /    |                 |     Device / Emulator     |
    |       Build Server        |                 |                           |
    |                           |                 |                           |
    |   ---------------------   | Json over HTTP  |   ---------------------   |
    |  |                     |--|-----------------|->|                     |  |
    |  |  Step Definitions,  |  |                 |  |   Instrumentation   |  |
    |  | Ruby Client Library |<-|-----------------|--|     Test Server     |  |
    |  |                     |  |                 |  |                     |  |
    |   ---------------------   |                 |   ---------------------   |
    |            / \            |                 |            / \            |
    |             |             |                 |             |             |
    |             |             |                 |             |             |
    |            \ /            |                 |            \ /            |
    |   ---------------------   |                 |   ---------------------   |
    |  |                     |  |                 |  |                     |  |
    |  |      Features       |  |                 |  |      Your app       |  |
    |  |                     |  |                 |  |                     |  |
    |  |                     |  |                 |  |                     |  |
    |   ---------------------   |                 |   ---------------------   |
    |___________________________|                 |___________________________|

```

3. Build the test server that will be used when testing the app, run

   -> bundle exec calabash-android build your-app.apk


4. Start the console, run

   -> bundle exec calabash-android console your-app.apk

    once the console is started, remember to run the following commands
    - reinstall_apps (to install the latest build apk)
    - start_test_server_in_background (to start the test server and to open the app)

    now you are ready to start querying the UI hierarchy using
    - query("*") (show all UI hierarchy)


5. Run the tests, run

   -> bundle exec calabash-android run your-app.apk



REFERENCES

https://github.com/calabash/calabash-android
