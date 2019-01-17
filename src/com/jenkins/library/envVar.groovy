package com.jenkins.library

public def VariablesName() {
 
        def ENV = [:]
        ENV.HTTP_PROXY='http://10.113.140.187:3128'
 		ENV.HTTP_PROXY='http://10.113.140.187:3128'
        ENV.HTTPS_PROXY='http://10.113.140.187:3128'
        ENV.http_proxy='http://10.113.140.187:3128'
        ENV.https_proxy='http://10.113.140.187:3128'
        ENV.no_proxy='localhost,127.0.0.1,sandbox.local,lbg.eu-gb.mybluemix.net,lbg.eu-gb.bluemix.net,10.113.140.170,10.113.140.179,10.113.140.187,10.113.140.168,jenkins.sandbox.extranet.group,nexus.sandbox.extranet.group,gerrit.sandbox.extranet.group,sonar.sandbox.extranet.group,management01.psd2.sandbox.extranet.group'

        ENV.SASS_BINARY_PATH='~/linux-x64-46_binding.node'

        ENV.CC='/apps/tools/devtoolset-1.1/root/usr/bin/gcc'
        ENV.CPP='/apps/tools/devtoolset-1.1/root/usr/bin/cpp'
        ENV.CXX='/apps/tools/devtoolset-1.1/root/usr/bin/c++'
        ENV.NODEJS_ORG_MIRROR='http://nexus.sandbox.extranet.group/nexus/content/sites/binaries/node'
        println "CPP = ${ENV.CPP}"
 }