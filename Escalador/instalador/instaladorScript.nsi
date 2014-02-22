 !include "MUI.nsh"
 !define mui_abortwarning
 #unistall icon
 ;-!define MUI_UNICON "examen.ico"
 
 ;---------Paginas 
 !insertmacro MUI_PAGE_WELCOME 
 !insertmacro MUI_PAGE_LICENSE "licencia.txt"
 !insertmacro MUI_PAGE_DIRECTORY 
 !insertmacro MUI_PAGE_INSTFILES 
 !insertmacro MUI_PAGE_FINISH
 #-p�ginas referentes al desinstalador
!insertmacro MUI_UNPAGE_WELCOME
!insertmacro MUI_UNPAGE_INSTFILES
!insertmacro MUI_UNPAGE_FINISH
 ;----------------------
 #Languages
 !insertmacro MUI_LANGUAGE "Spanish"
 ;---------------------
 Name "Escalador"
 OutFile "Escalador.exe"
 Icon "icono.ico"
 InstallDir "$DESKTOP\Escalador"
 RequestExecutionLevel user
 DirText "Elija un directorio donde instalar la aplicaci�n:"
 #Indicamos que cuando la instalaci�n se complete no se cierre el instalador autom�ticamente
 AutoCloseWindow false
 #Mostramos todos los detalles del la instalaci�n al usuario.
 ShowInstDetails show
 #En caso de encontrarse los ficheros se sobreescriben
 SetOverwrite on
 #Optimizamos nuestro paquete en tiempo de compilaci�n, es altamente recomendable habilitar siempre esta opci�n
 SetDatablockOptimize on
 UninstallText "Este es el desinstalador de la aplicacion."
 ;-------------------------
 Section "programa"
    setOutPath $INSTDIR
	
	File Escalador.jar
	File licencia.txt
	File icono.ico
	File /r lib
	File /r database
	File /r help
	
    WriteUninstaller "$INSTDIR\uninstall.exe"
	
	CreateShortCut "$DESKTOP\Escalador.lnk" "$INSTDIR\Escalador.jar" "" "$INSTDIR\icono.ico"
	CreateDirectory "$SMPROGRAMS\Escalador"
    CreateShortCut "$SMPROGRAMS\Escalador\Desinstalar.lnk" "$INSTDIR\uninstall.exe"
	
SectionEnd

Section "uninstall"
 
    Delete "$INSTDIR\uninstall.exe"
	Delete "$DESKTOP\Escalador.lnk"
	RMDir /r "$INSTDIR"  
	RMDir /r "$SMPROGRAMS\Escalador"
	
SectionEnd
 
 
 
 
 
 
 
 