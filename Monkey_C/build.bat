@call clean.bat
call monkeyc -f monkey.jungle -o bin\Katas.prg -y ..\developer_key -d vivoactive5_sim -w -l 3 %*
@rem -w ... warnings
@rem -l 3 ... strict type checker
