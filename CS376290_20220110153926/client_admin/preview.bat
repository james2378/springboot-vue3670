@echo off
call npm i
call npm run build
cd dist
call deploy.bat

IF ERRORLEVEL 1 goto ERRORHANDLER
IF ERRORLEVEL 0 goto DONE

:ERRORHANDLER
echo 部署管理前端服务出错
pause


:DONE
exit