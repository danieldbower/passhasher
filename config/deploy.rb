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


# If you are using Passenger mod_rails uncomment this:
# if you're still using the script/reapear helper you will need
# these http://github.com/rails/irs_process_scripts

 namespace :deploy do
   task :start do ; end
   task :stop do ; end
   task :restart, :roles => :app, :except => { :no_release => true } do
     run "#{try_sudo} touch #{File.join(current_path,'tmp','restart.txt')}"
   end
 end
