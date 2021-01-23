# JishoParser

**Newest version:** `0.2`

## Introduction
This is the very beginning of the JishoParser project which aims to make learning Japanese vocabulary a little bit easier.

## What does it do?
Simply speaking JishoParser integrates with [Jisho.org](https://jisho.org/) API, fetches data per criteria and converts it to the more "learning friendly" format. 
At this moment it works as CLI application and provides a file output. This may change in the future, more features and output formats are planned.

## How does it work?

### Requirements
```Java >= 8```

### Getting
```git clone https://github.com/Peteef/jisho-parser.git``` (if you have Git) or just *Download ZIP*.

### Running
>Take a note that `/stable` catalog contains current release version.

```java -jar jisho-parser-{version}.jar [arguments]```

For list of available arguments use `--help`. These are some important arguments:

**Criteria:**

|Argument(s)|Default|Description|
|-----------|-------|-----------|
|`--jlpt5` / `--jlpt4` / `--jlpt3` / `--jlpt2` / `--jlpt1`|`--jlpt5`|JLPT level (at this moment it's only possible to select one per application run).

**Output formats:**

At this moment it's only possible to select one per application run. Default: `--text-to-file`

|Argument(s)|Description|
|-----------|-----------|
|`--json-to-file`|Provides simple `.json` file with essential fields.|
|`--text-to-file`|Provides simple `.txt` file with entries in a simple, readable format.|
|`--excel-to-file`|Provides simple `.xlsx` file with entries in a simple, readable format.|

---

**For developers:**
### Building
```./gradlew clean build```

Output jar is located inside ```/build/libs```.

### Testing
```./gradlew test```

---

*Created by Peteef, 2021.*\
よろしくお願いします!