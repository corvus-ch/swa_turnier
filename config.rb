# Require any additional compass plugins here.

# Set this to the root of your project when deployed:
http_path = "/"
css_dir = "src/main/webapp/resources/css"
sass_dir = "src/main/resources/scss"
images_dir = "src/main/webapp/resources/images"
javascripts_dir = "src/main/webapp/resources/js"

# Have a readable css output.
output_style = :expanded

# Do not use absolute paths.
relative_assets = true

# Add line comments when developing.
line_comments = (environment == :development)

require 'bootstrap-sass'

