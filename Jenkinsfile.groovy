node {

    properties([parameters([choice(choices:
     ['golden_ami', 'tower', 'elk', 'nagiosxi', 'gitlab', 'nexus', 'vault'],
      description: 'What tool would you like to build?', 
      name: 'TOOL_TO_PROVISION'), 
      choice(choices: ['us-east-1', 'us-east-2', 'us-west-1', 'us-west-2'],
       description: 'Choose region',
        name: 'AMI_REGION')])])
    stage("Pull Repo"){
        git 'https://github.com/Mahsuda/packer.git'
        }

    stage("Build an image"){
        sh "packer version"
        //sh "packer build -var region=${AMI_REGION} tools/jenkins_example.json"
        }

    stage("Send notification to slack"){
        slackSend channel: 'nagios_alert ', message: 'Image_built'
        }

    stage("Send email"){
        echo "Hello"
        }
}