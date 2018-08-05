

$>  git status

$>  git remote -v
DESCRIPTION: List remote repos

$>  git branch
DESCRIPTION: List branches

$>  git branch dev/addLib0508
$>  git checkout dev/addLib0508
DESCRIPTION:  Creates a branch and checkouts out the branch, meaning it becomes the active one
DESCRIPTION:  Notice these two commands are the same as "git checkout -b dev/addLib0508"


ACTION: Now add a file to the current folder. I added src/java/DateBuilderDef.java

$>  git add .
DESCRIPTION: It stages all the files
DESCRIPTION: Notice one can choose what to commit. One can commit in different stages

$>  git commit -m "Added DateBuilder src file"
DESCRIPTION: Commits changes with provided commit message. 
IMPORTANT: One needs to add files first before calling commit. Always!

ACTION: Modify file to include license at the beginning of file followed by a commit

$>  git add .
$>  git commit -m "Added license to DataBuilder"

ACTION: Modify file to include comment header at the beggining of each function followed by a commit

$>  git add .
$>  git commit -m "Preparing DataBuilder for docs based on comments"

$> git log
DESCRIPTION: Shows workflow/commit history

$>  git checkout master
$>  git merge dev/addLib0508
DESCRIPTION: These are the step to bring the changes from your dev branch into the master branch
DESCRIPTION: Steps are important. First you need to switch to master. Then you merge the dev branch into master

$>  git git pull origin master
$>  git git push
DESCRIPTION: These are the step to upload the changes from your local master to the origin master.
DESCRIPTION: "Origin master" refers to the online repo.
DESCRIPTION: The first command wil ask you to add a commit message. The interface is based on vi (*** Additional notes)
DESCRIPTION: The second command will require you to input your github credentials: username & password


NOTES:

*** Additional notes: VI instructions


FINAL REMARKS

Now this is the workflow to work with a branch. This workflow might change in the future in case something is not clear or
to change the design for a better flow. From what I am concern, this is a great starting point.
We might ran into problems in the future. They will be addresses as they come






