# FinVisor
Open Source advanced personal finance advisor engine


## Objective
This project allows individuals to forecast, estimate and predict their personal finances using popular finance algorithms, machine learning and statistics libraries.

## Usage
For now, the application produces and executable jar file that you can run from your command line.

```bash
mvn clean install
```
You will find the executable jar inside "target" folder. Now you are able to execute it using the standard java -jar command.

You need to supply a file with your personal finance configuration. You can find an example of such file inside configuration/vlad.json. To pass it to the application you need to specify -f path/to/configuration.json .

