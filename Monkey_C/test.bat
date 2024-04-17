:build
@echo === BUILD ===
@call build.bat -t
@IF '%ERRORLEVEL%'=='0' GOTO test
@ruby -e "puts \"\033[33mBUILD FAILED\033[0m\""
@goto end

:test
@echo === TEST ===
@call run.bat /t %*
@type out.txt | ruby -e "if $_ =~ /^PASS/ then puts \"\033[32m#{$_.chomp}\033[0m\" elsif $_ =~ /^FAIL|^ERROR/ then puts \"\033[31m#{$_.chomp}\033[0m\" elsif $_ =~ /^PASS/ then puts \"\033[33m#{$_.chomp}\033[0m\" else puts $_.chomp end" -n
@del out.txt

:end
