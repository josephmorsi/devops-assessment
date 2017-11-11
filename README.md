# CheckRecipient DevOps assessment

## Intro

### Assessment Rules
In general, there are no rules, but a couple of useful hints:

- Take as much time as you want
- Please briefly explain your solutions
- Share even if you don't finish all the tasks
- If not specified, the technology you'll use is completely at your
  discretion. We understand there's more than one way to do things.

Please know that by accepting the challenge you agree to the following:

- Keep the assessment and your solution to yourself.
- Keep the git repo private.
- You, and you alone, are expected to be the author of the solutions for
  this assessment.

### Issues
All bugs are intentional, to better test your debugging skills :), but if
you're really convinced something is broken, please open a Github ticket.

### Proposed Tasks
There are two types of tasks:

- **Technical tasks** meant to be implemented and tested on the Vagrant box. Any
  code changes must be committed and pushed to a private git repository.
**You're expected to automate all the tasks in configuration tool of your choice
(Puppet/Ansible/Chef/Salt...).**

- **Written tasks** - just write a proposal of the solution and/or a prototype
  configuration, you don't need to test it.

Tasks marked as **bonus** are optional.

### Feedback
We would love to hear feedback on this assessment and suggestions
for any improvements, whether technical or not.

Good luck!

## Requirements
We have shared a git repository with a Vagrant environment. This
has been tested on Mac OS X, but should work on any modern Linux desktop as
well. Do not hesitate to ask if you have any problems.

### Prerequisites
- internet connection
- Vagrant
- VirtualBox
- ruby >= 2.0
- bundler ruby gem

## Tasks

### Task 1: Get started

- Fork this repository

*Commit all your changes to your fork of the repo as you progress through this
assessment.*

- Install all the requirements listed above.
- Run `bundle install`
- Run `vagrant up` to start the challenge VM
- Test connectivity by running `vagrant ssh`

### Task 2: Missing motd

There's a file `/etc/motd`, but nothing is displayed when you ssh in. Can you
fix this?
```
YOUR ANSWER GOES HERE
 sudo nano /etc/ssh/sshd_config and change PrintMotd to yes
```
 
### Task 3: Install Docker

- Install Docker (or another preferred container runtime) on the VM.

*You're expected to automate all the tasks in configuration tool of your choice
(Puppet/Ansible/Chef/Salt...).*
2 ways to run this
   1- using bash script during vm provision
   2- ansible playbook

### Task 4: Deploy sample web app

There's simple Java web service - `files/demo.jar`. You can start it by
executing `./start.sh` in `files` directory. This should start simple java web
service app on port 1234, you can test that it's up by running
`curl localhost:1234`. Expected output is something like `... Internal Server
Error ... Error connecting to DB`.
 ...there is an issue ehere as java is required to be installed first
    - installed java

- Package this service as a container (don't forget to commit Dockerfile or
  equiv.).
- Use configuration tool of your choice to:
  - Build the container
  - Deploy the container on the VM and expose app on port 8080
  - Test that application is listening and responds to the http requests
- **Bonus**: Make proper init-script or systemd unit file, so that the app can be
  treated as a service (eg. `systemctl [start|stop|status]`).

### Task 5: Deploy MySQL DB and populate it with data
App that we installed in the previous step is trying to read data from
a database. In this step you should deploy MySql database and populate it with
data. Again, automate this process with the tool of your choice.

- Deploy MySql as a container on the VM
- Verify mysql is running and listening on localhost, port 3306
- Populate db with data from `files/data.sql` file
- You can verify that app can connect to db by running `curl localhost:8080` and
  expect output like `${hostname}: Hello, I'm 0.1 DB: CHECKRECIPIENT,
  CHALLENGE`.

### Task 6: Install and configure reverse proxy
Install and configure reverse proxy (of your choice) to serve the demo app on
port 80. Again automate following with tool of your choice.

- Install reverse proxy
- Make sure it starts and listens on port 80
- Make sure it proxies to demo app

- **Bonus**: Serve page at `/health` endpoint, which returns HTTP 200, with page
  content OK when the service is up.

### Task 7: Secure the server
We want to block the network access to this machine. Automate firewall configuration:

- Block all the ports
- Allow ssh from host network - ie. 10.0.0.0/16
- Allow all access from localhost.
- Open HTTP for everyone.

- After securing the firewall, propose other hardening steps you would take to
  make the server secure (do not implement these, only paste proposed solution
  below).

  ```
  YOUR ANSWER GOES HERE
  on an AWS enviorment we can create a security group that only allows traffic to the server/s from the required IP addresses
  ```

### Task 8: Propose zero downtime deployments for the app
We want to implement zero downtime deployments of our application. Write
a couple of lines about what approach would you take and how would you
implement this, eventually what tools would you use.

You can assume that any two versions of demo app can work at the same time and
they scale horizontally.

How would you handle eventual changes in DB schema?

```
YOUR ANSWER GOES HERE
to achive zero downtime a HA proxy solution would be recomended and a doublicate frontend server be configured behind the HA proxy
```

## Finally
Once you're done, please share the link to the forked Github repo
with us and make sure you have committed and pushed everything.

**Please don't forget to grant us (`stepan-plutoflume`) permissions to read the fork.**

We would also love to hear any feedback about this assessment and feel free to
ask any questions about the position as well.

Finally, attach any additional information you want to share with us (GitHub
profile, blog...) and notify us/recruiter that you're done.

Thank you, we'll be in touch!
