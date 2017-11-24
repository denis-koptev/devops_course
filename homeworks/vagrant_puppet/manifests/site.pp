Exec { path => '/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin' }

include user
include apt
include mysql
include python
include virtualenv
include software


class user {
  exec { 'add user':
    command => "sudo useradd -m -G sudo -s /bin/bash ${user}",
    unless => "id -u ${user}"
  }

  exec { 'set password':
    command => "echo \"${user}:${password}\" | sudo chpasswd",
    require => Exec['add user']
  }

  # Prepare user's project directories
  file { ["/home/${user}/virtualenvs"]:
    ensure => directory,
    owner => "${user}",
    group => "${user}",
    require => Exec['add user']
  }
}

class apt {
  exec { 'apt-get update':
    timeout => 0
  }

  package { 'python-software-properties':
    ensure => latest,
    require => Exec['apt-get update']
  }

  exec { 'last ppa':
    command => 'add-apt-repository ppa:git-core/ppa',
    require => Package['python-software-properties']
  }

  exec { 'apt-get update again':
    command => 'apt-get update',
    timeout => 0,
    require => Exec['last ppa']
  }
}

class mysql {
  $create_db_cmd = "CREATE DATABASE ${db_name} CHARACTER SET utf8;"
  $create_user_cmd = "CREATE USER '${db_user}'@localhost IDENTIFIED BY '${db_password}';"
  $grant_db_cmd = "GRANT ALL PRIVILEGES ON ${db_name}.* TO '${db_user}'@localhost;"

  package { 'mysql-server':
    ensure => latest,
    require => Class['apt']
  }

  package { 'libmysqlclient-dev':
    ensure => latest,
    require => Class['apt']
  }

  service { 'mysql':
    ensure => running,
    enable => true,
    require => Package['mysql-server']
  }

  exec { 'grant user db':
    command => "mysql -u root -e \"${create_db_cmd}${create_user_cmd}${grant_db_cmd}\"",
    unless => "mysqlshow -u${db_user} -p${db_password} ${db_name}",
    require => Service['mysql']
  }
}

class python {

  package { 'python3':
    ensure => latest,
    require => Class['apt']
  }

  package { 'python3-pip':
    ensure => latest,
    require => Class['apt']
  }
}

class virtualenv {

  exec { 'install virtualenv':
    command => "pip3 install virtualenv",
    require => Class['python']
  }

  exec { 'create virtualenv':
    command => "virtualenv --always-copy ${domain_name}/env",
    cwd => "/home/${user}/virtualenvs",
    user => $user,
    require => Exec['install virtualenv']
  }

  file { "/home/${user}/virtualenvs/${domain_name}/requirements.txt":
    ensure => file,
    owner => "${user}",
    group => "${user}",
    mode => 0644,
    source => "${inc_file_path}/virtualenv/requirements.txt",
    require => Exec['create virtualenv']
  }

}

class software {
  package { 'git':
    ensure => latest,
    require => Class['apt']
  }

  package { 'vim':
    ensure => latest,
    require => Class['apt']
  }
}
