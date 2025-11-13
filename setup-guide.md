# Setup Guide

This guide will help you set up your development environment for the Advanced Programming course. Follow these steps carefully to ensure everything works correctly.

## Required Software

You need to install:
1. Java Development Kit (JDK) 21
2. Maven (build tool)
3. IntelliJ IDEA (IDE)
4. Git (version control) - Strongly recommended

## Step 1: Install Java 21 LTS

### Windows

1. Download Java 21 from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [Adoptium](https://adoptium.net/)
2. Run the installer
3. Follow the installation wizard (default settings are fine)
4. Verify installation:
   ```bash
   java -version
   ```
   Should show: `java version "21.x.x"`

### macOS

**Using Homebrew (recommended):**
```bash
brew install openjdk@21
```

**Verify installation:**
```bash
java -version
```

### Linux

**Ubuntu/Debian:**
```bash
sudo apt update
sudo apt install openjdk-21-jdk
```

**Fedora:**
```bash
sudo dnf install java-21-openjdk-devel
```

**Verify installation:**
```bash
java -version
```

## Step 2: Install Maven

Maven is a build automation tool for Java projects.

### Windows

1. Download Maven from [maven.apache.org](https://maven.apache.org/download.cgi)
2. Extract to `C:\Program Files\Apache\maven`
3. Add to PATH:
   - System Properties → Environment Variables
   - Edit `Path` variable
   - Add `C:\Program Files\Apache\maven\bin`
4. Verify:
   ```bash
   mvn -version
   ```

### macOS

```bash
brew install maven
```

**Verify:**
```bash
mvn -version
```

### Linux

**Ubuntu/Debian:**
```bash
sudo apt install maven
```

**Fedora:**
```bash
sudo dnf install maven
```

**Verify:**
```bash
mvn -version
```

## Step 3: Install IntelliJ IDEA

IntelliJ IDEA is the recommended IDE for this course. The Community Edition (free) is sufficient.

1. Download from [jetbrains.com/idea/download](https://www.jetbrains.com/idea/download/)
2. Choose **Community Edition** (free)
3. Run the installer
4. Follow the installation wizard

### First-Time Setup

1. Launch IntelliJ IDEA
2. Skip importing settings (if first time)
3. Choose a theme (personal preference)
4. Install recommended plugins (accept defaults)

### Verify Java and Maven Integration

1. Open IntelliJ IDEA
2. Create a new project:
   - File → New → Project
   - Select "Maven"
   - JDK: Select Java 21
   - Click "Next" and "Finish"
3. If Java 21 is not listed:
   - Click "Download JDK"
   - Select version 21
   - Download and configure

## Step 4: Install Git (Strongly Recommended)

Git is essential for version control and professional development.

### Windows

Download Git from [git-scm.com](https://git-scm.com/download/win) and run the installer.

**Important settings during installation:**
- Use "Git Bash" as the terminal
- Default branch name: "main"
- All other defaults are fine

**Verify:**
```bash
git --version
```

### macOS

```bash
brew install git
```

**Verify:**
```bash
git --version
```

### Linux

**Ubuntu/Debian:**
```bash
sudo apt install git
```

**Fedora:**
```bash
sudo dnf install git
```

**Verify:**
```bash
git --version
```

### Configure Git

Set your name and email (use your real information):

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

## Step 5: Create GitHub Account (Strongly Recommended)

1. Go to [github.com](https://github.com)
2. Sign up for a free account
3. Verify your email address

**Why GitHub?**
- Back up your work
- Track your progress
- Build your portfolio
- Learn professional workflows

See [how-to-use-github.md](how-to-use-github.md) for detailed Git and GitHub usage.

## Step 6: Test Your Setup

Let's verify everything works together.

### 1. Create a Test Project

```bash
# Create a directory for testing
mkdir test-setup
cd test-setup

# Create a simple Maven project
mvn archetype:generate \
  -DgroupId=com.test \
  -DartifactId=hello-world \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.4 \
  -DinteractiveMode=false

cd hello-world
```

### 2. Build the Project

```bash
mvn clean compile
```

You should see: `BUILD SUCCESS`

### 3. Run Tests

```bash
mvn test
```

You should see: `BUILD SUCCESS` with test results

### 4. Open in IntelliJ

1. Open IntelliJ IDEA
2. File → Open
3. Navigate to `test-setup/hello-world`
4. Click "Open"
5. IntelliJ will import the Maven project
6. Wait for indexing to complete

### 5. Run from IntelliJ

1. Navigate to `src/main/java/com/test/App.java`
2. Right-click in the editor
3. Select "Run 'App.main()'"
4. You should see "Hello World!" in the console

### 6. Verify Java 21 Features

Create a new file `test-setup/hello-world/src/main/java/com/test/RecordTest.java`:

```java
package com.test;

// Java records (Java 16+, we're using 21)
record Person(String name, int age) {
    // Records are immutable data carriers
}

public class RecordTest {
    public static void main(String[] args) {
        Person person = new Person("Alice", 30);
        System.out.println(person);
        System.out.println("Name: " + person.name());
        System.out.println("Age: " + person.age());
    }
}
```

Run this file. If it works, Java 21 is properly configured!

## Troubleshooting

### "java: invalid source release: 21"

**Problem:** IntelliJ is using the wrong Java version.

**Solution:**
1. File → Project Structure → Project
2. Set SDK to Java 21
3. Set Language Level to "21 - Record patterns..."
4. File → Settings → Build, Execution, Deployment → Compiler → Java Compiler
5. Set "Project bytecode version" to 21

### "mvn: command not found"

**Problem:** Maven is not in your PATH.

**Solution:**
- Reinstall Maven and ensure it's added to PATH
- Restart your terminal/command prompt
- On Windows, restart your computer if needed

### IntelliJ doesn't recognize Maven project

**Problem:** IntelliJ hasn't imported the Maven project properly.

**Solution:**
1. Right-click on `pom.xml`
2. Select "Maven" → "Reload Project"
3. Wait for indexing to complete

### Git command doesn't work in IntelliJ terminal

**Problem:** IntelliJ's terminal doesn't have Git in PATH.

**Solution:**
1. File → Settings → Tools → Terminal
2. Ensure "Shell path" is correct
3. Restart IntelliJ

## You're All Set!

Once all tests pass, you're ready to start the course!

Next steps:
1. Read [how-to-use-github.md](how-to-use-github.md) to set up version control
2. Navigate to `Week01-NaiveApproach/` to begin
3. Complete the pre-class reading and exercises

## Need Help?

If you encounter issues not covered here:
- Ask in class
- Check with classmates
- Consult the official documentation:
  - [Java docs](https://docs.oracle.com/en/java/javase/21/)
  - [Maven docs](https://maven.apache.org/guides/)
  - [IntelliJ docs](https://www.jetbrains.com/help/idea/)
  - [Git docs](https://git-scm.com/doc)

Good luck! 🚀
