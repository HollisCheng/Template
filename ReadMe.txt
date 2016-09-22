C:\Program Files\Java\jre1.8.0_60\bin

keytool -exportcert -alias androiddebugkey -keystore %HOMEPATH%\.android\debug.keystore | openssl sha1 -binary | openssl base64

pw:android

ZN54Bx6SWHYlFu5I+MTsNWL98fY=

release key

C:\Program Files\Java\jdk1.8.0_51\bin>

keytool -exportcert -alias YOUR_RELEASE_KEY_ALIAS -keystore YOUR_RELEASE_KEY_PATH | openssl sha1 -binary | openssl base64

keytool -exportcert -alias HollisKey -keystore "C:\Users\hollischeng\DocumentsTemplate\HollisKey.jks" | openssl sha1 -binary | openssl base64

S2WlVi/mTorO3M4HsqLbrDwaSf0=

HollisKey holliskey android studio newworld