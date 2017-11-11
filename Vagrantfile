Vagrant.configure('2') do |config|

  config.vm.hostname = 'devops-challenge'
  config.vm.box = 'puppetlabs/centos-7.2-64-puppet'
  config.vm.box_version = '1.0.1'
  config.vm.synced_folder "src/", "/devops-assessment-master/", disabled: true
  config.vm.network "private_network", ip: "192.168.50.4"
  forwarded_ports = {'80' => '18080', '8000' => '8000', '1234' => '1234' }
   #config.vm.network “private_network”, ip: “10.0.0.2”
  forwarded_ports.each do |k,v|
    config.vm.network :forwarded_port, guest: "#{k}",  host: "#{v}", auto_correct: true
  end

  config.vm.provider :virtualbox do |virtualbox, override|
    virtualbox.customize ['modifyvm', :id, '--memory', '1024']
  end

  config.ssh.forward_agent = true
    config.vm.provision "shell", path: "setup/ruby.sh"
    config.vm.provision "shell", path: "setup/java.sh"
    #config.vm.provision "shell", path: "setup/puppet.sh"
    config.vm.provision "shell", path: "setup/motd.sh"
    config.vm.provision "shell", path: "setup/docker.sh"

  config.vm.provision :puppet do |puppet|
    puppet.manifests_path       = 'manifests'
    puppet.module_path          = 'modules'
    puppet.manifest_file        = ''
    puppet.hiera_config_path    = 'hiera.yaml'
    puppet.options              = '--verbose'
    puppet.facter = {
      "role" => "challenge"
    }
  end
end
