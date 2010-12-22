Rails.application.config.middleware.use OmniAuth::Builder do
  provider OmniAuth::Strategies::CAS, :cas_server => 'https://localhost/cas'
  provider :twitter, 'key', 'secret'
end

