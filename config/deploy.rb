# Use RVM plugin
$:.unshift(File.expand_path('./lib', ENV['rvm_path'])) # Add RVM's lib directory to the load path.

require "rvm/capistrano"                  # Load RVM's capistrano plugin.
require "database_yml/capistrano"

set :rvm_ruby_string, 'ree'        # Or whatever env you want it to run in.

# Sudo requires password - try the following
default_run_options[:pty] = true

set :application, "passhasher"

set :repository,  "git://github.com/danieldbower/passhasher.git"
set :scm, :git

set :deploy_to, "/var/www-rails/passhasher"
role :web, "bowerstudios"                          # using ssh shortcut
role :app, "bowerstudios"                          # using ssh shortcut
role :db,  "bowerstudios", :primary => true 

 namespace :deploy do
   task :start do ; end
   task :stop do ; end
   task :restart, :roles => :app, :except => { :no_release => true } do
     run "#{try_sudo} touch #{File.join(current_path,'tmp','restart.txt')}"
   end
 end

 namespace :omniauth do
  task :setup, :except => { :no_release => true } do
    run "mkdir -p #{shared_path}/config"

    upload "config/initializers/omniauth.rb.example", "#{shared_path}/config/omniauth.rb.example"

    run <<-CMD
      test -e #{shared_path}/config/omniauth.rb || {
        cp -f #{shared_path}/config/omniauth.rb.example #{shared_path}/config/omniauth.rb &&
        chmod 600 #{shared_path}/config/omniauth.rb; }
    CMD
  end

  task :symlink, :except => { :no_release => true } do
    run "ln -nfs #{shared_path}/config/omniauth.rb #{release_path}/config/initializers/omniauth.rb"
  end

  after "deploy:setup", "omniauth:setup"
  after "deploy:finalize_update", "omniauth:symlink"
end
