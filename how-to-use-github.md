# How to Use Git and GitHub

This guide introduces you to Git (version control) and GitHub (online hosting platform). Using these tools is **strongly recommended** for this course and essential for professional software development.

## Why Use Git and GitHub?

### Git (Version Control System)
- **Safety**: Never lose your work - every change is saved
- **History**: See what changed, when, and why
- **Experimentation**: Try new ideas without breaking working code
- **Undo**: Go back to any previous version

### GitHub (Online Platform)
- **Backup**: Your code is safe even if your computer fails
- **Portfolio**: Show your work to potential employers
- **Collaboration**: Share code and get feedback (optional)
- **Professional skill**: Used by virtually all software companies

## Basic Concepts

### Repository (Repo)
A project folder tracked by Git. Contains your code and its entire history.

### Commit
A snapshot of your code at a point in time. Think of it as a save point in a game.

### Push
Upload your commits to GitHub (backup to the cloud).

### Pull
Download commits from GitHub (sync changes).

### Clone
Download a repository from GitHub to your computer.

## Getting Started

### 1. Install Git

If you haven't already, follow [setup-guide.md](setup-guide.md) to install Git.

### 2. Configure Git

Tell Git who you are (only needed once):

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

### 3. Create a GitHub Account

1. Go to [github.com](https://github.com)
2. Sign up for a free account
3. Verify your email

## Workflow for This Course

### Option A: Create Your Own Repository (Recommended)

Create one repository for all your course work.

#### 1. Create Repository on GitHub

1. Log in to GitHub
2. Click the "+" in the top right
3. Select "New repository"
4. Name it: `advanced-programming-2024` (or current year)
5. Description: "My work for Advanced Programming course"
6. Select "Private" (your choice)
7. Check "Add a README file"
8. Click "Create repository"

#### 2. Clone to Your Computer

```bash
# Navigate to where you want to store course work
cd ~/Documents  # or your preferred location

# Clone the repository
git clone https://github.com/YOUR-USERNAME/advanced-programming-2024.git

# Enter the directory
cd advanced-programming-2024
```

#### 3. Organize Your Work

```bash
# Create folders for each week
mkdir Week01 Week02 Week03
# (etc.)
```

#### 4. Daily Workflow

**After working on exercises:**

```bash
# See what changed
git status

# Add changed files
git add .

# Create a commit (save point)
git commit -m "Complete Week 1 pre-class exercises"

# Upload to GitHub
git push
```

**Before starting new work:**

```bash
# Make sure you have latest version (if working from multiple computers)
git pull
```

### Option B: Fork the Course Repository

If the course materials are on GitHub, you can fork them.

1. Go to the course repository on GitHub
2. Click "Fork" (top right)
3. Clone your fork to your computer
4. Work in your fork

## Common Git Commands

### Check Status
```bash
git status
```
Shows what files changed since last commit.

### Add Files
```bash
# Add specific file
git add filename.java

# Add all changed files
git add .

# Add all files in a folder
git add Week01/
```

### Commit (Save Point)
```bash
git commit -m "Brief description of what you did"
```

**Good commit messages:**
- ✅ "Complete Week 1 pre-class exercises"
- ✅ "Fix bug in game combat system"
- ✅ "Add tests for List interface"

**Bad commit messages:**
- ❌ "stuff"
- ❌ "changes"
- ❌ "asdf"

### Push (Upload)
```bash
git push
```

### Pull (Download)
```bash
git pull
```

### View History
```bash
# See commit history
git log

# See history with changes
git log --patch

# See short history
git log --oneline
```

### Undo Changes

**Undo uncommitted changes to a file:**
```bash
git restore filename.java
```

**Undo all uncommitted changes:**
```bash
git restore .
```

**Go back to a previous commit:**
```bash
# See history to find commit hash
git log --oneline

# View that commit (read-only)
git checkout abc1234  # replace with actual hash

# Return to latest
git checkout main
```

## Using Git in IntelliJ IDEA

IntelliJ has excellent Git integration built-in.

### Initial Setup

1. VCS → Enable Version Control Integration
2. Select "Git"

### Common Operations

**Commit:**
1. Ctrl+K (Windows/Linux) or Cmd+K (Mac)
2. Select files to commit
3. Write commit message
4. Click "Commit" or "Commit and Push"

**Push:**
1. Ctrl+Shift+K (Windows/Linux) or Cmd+Shift+K (Mac)
2. Review commits
3. Click "Push"

**View History:**
1. Right-click file or folder
2. Git → Show History

**Compare Versions:**
1. Right-click file
2. Git → Compare with...
3. Choose commit to compare

## Best Practices

### Commit Often
- Commit after completing each exercise
- Commit when you get something working
- Commit before trying something risky

### Write Clear Messages
- Explain *what* you did, not *how*
- "Add combat system" not "Add 3 new methods"
- Future you will thank present you

### Push Regularly
- Push at least once per day
- Push before closing your laptop
- Push after completing major work

### Don't Commit Generated Files
Files to **NOT** commit:
- `target/` (Maven build output)
- `.idea/workspace.xml` (IntelliJ workspace)
- `*.class` (compiled files)
- `*.log` (log files)

Create a `.gitignore` file:
```
# Maven
target/
*.class

# IntelliJ
.idea/workspace.xml
.idea/tasks.xml
*.iws

# OS files
.DS_Store
Thumbs.db
```

### Keep Sensitive Information Out
Never commit:
- Passwords
- API keys
- Database connection strings with credentials

## Helpful Git Resources

### Interactive Learning
- [Learn Git Branching](https://learngitbranching.js.org/) - Interactive tutorial
- [GitHub Skills](https://skills.github.com/) - Official GitHub tutorials

### Reference
- [Git Cheat Sheet](https://education.github.com/git-cheat-sheet-education.pdf)
- [GitHub Docs](https://docs.github.com/)

### Books
- [Pro Git](https://git-scm.com/book/en/v2) - Free online book

## Troubleshooting

### "Repository not found" when pushing
**Problem:** Git doesn't know where to push.

**Solution:**
```bash
git remote -v  # Check current remote
git remote set-url origin https://github.com/YOUR-USERNAME/your-repo.git
```

### Merge conflicts
**Problem:** Git can't automatically combine changes.

**Solution:**
1. Open the conflicted file
2. Look for `<<<<<<<`, `=======`, `>>>>>>>` markers
3. Decide which version to keep
4. Remove the markers
5. `git add filename.java`
6. `git commit`

### Accidentally committed wrong files
**Problem:** Committed something you shouldn't have.

**Solution:**
```bash
# If you haven't pushed yet
git reset HEAD~1  # Undo last commit, keep changes

# If you already pushed
git revert HEAD  # Create new commit that undoes the last one
```

### Want to start over
**Problem:** Everything is messed up.

**Solution:**
```bash
# Save your current work first!
cp -r myproject myproject-backup

# Then either:
git reset --hard origin/main  # Discard all local changes
# Or re-clone
cd ..
rm -rf myproject
git clone https://github.com/YOUR-USERNAME/your-repo.git
```

## Advanced Topics (Optional)

Once comfortable with basics, explore:

### Branches
Work on features independently:
```bash
git branch feature-name
git checkout feature-name
# ... work ...
git checkout main
git merge feature-name
```

### Tags
Mark important points (e.g., week completions):
```bash
git tag week01-complete
git push --tags
```

### GitHub Features
- **Issues**: Track tasks and bugs
- **Pull Requests**: Review code before merging
- **GitHub Pages**: Host project websites
- **Actions**: Automate testing and deployment

## You're Ready!

Start using Git from Week 1:
1. Create your repository
2. Clone it to your computer
3. Work on exercises
4. Commit frequently
5. Push regularly

Questions? Ask in class or check the resources above.

Happy coding! 🚀
