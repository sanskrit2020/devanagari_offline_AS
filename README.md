
### Initial setup on phone - only once per session


1. Connect mobile

2. Open terminal. Check if USB debugging is enabled

```sh
cd /tmp
adb devices

```

The output should look like this (with some random device code number)

```sh
name@computer: /tmp $ adb devices
List of devices attached
TF224JHN5	device
```

### Edit/create HTML files :-)

3. Go to https://github.com/sanskrit2020/devanagari_offline_AS

4. Edit/create your files inside this folder

![Files](https://github.com/sanskrit2020/devanagari_offline_AS/blob/master/img/listallfiles.png)

```sh
devanagari_offline_AS/app/src/main/assets/
```

5. Click "Commit Changes at the bottom of the page". green color button.

![Files](https://github.com/sanskrit2020/devanagari_offline_AS/blob/master/img/commit.png)

6. Click on Actions. See screenshot:
![Files](https://github.com/sanskrit2020/devanagari_offline_AS/blob/master/img/actions.png)


### Compilation

7. Everytime you save/commit changes it will AUTOMATICALLY COMPILE APK.

See the newest change/edit/add you made.

![Files](https://github.com/sanskrit2020/devanagari_offline_AS/blob/master/img/compile.png)

8. Now github starts compiling. See if it is in progress (brown).

![Files](https://github.com/sanskrit2020/devanagari_offline_AS/blob/master/img/inprogress2.png)


9. Wait for to turn green. 

![Files](https://github.com/sanskrit2020/devanagari_offline_AS/blob/master/img/green.png)



Check if tick mark is green. Means successfully compiled.
Red: Failed.

Then click on the title "I added new stothram" to download.

Github will download this as ZIP file:  **devanagiri-offline-debug.apk.zip**

![Files](https://github.com/sanskrit2020/devanagari_offline_AS/blob/master/img/jobcomplete.png)

10. Download the file devanagiri-offline-debug.apk. Copy to ``` /tmp ``` folder

11. Open terminal

```sh
cd /tmp
unzip devanagiri-offline-debug.apk.zip
adb install app-debug.apk
```


