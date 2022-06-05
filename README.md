# MongoDB

## Running Code
Install gradle on you machine using homebrew
```bash
brew install gradle
```
**In terminal**:

first build project using:
```bash
./gradlew clean build
```

Then pass the input file the program:
```bash
cat inputFile | ./gradlew run
```

## Libraries used

**com.github.wnameless:json-flattener**
Used to flatten json with 

**org.json:json**
Used to validate json

**com.google.code.gson:gson**
Used to do pretty json print

## Program Flow

1. Read input file
2. Validate json:
   1. Correct Json format
   2. Doesn't contain array
3. Flatten input json 
4. Pretty print output json




