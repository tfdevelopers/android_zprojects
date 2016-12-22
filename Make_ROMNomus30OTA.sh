#!/bin/bash
rm -r out/dist
rm -r out/target/product/s30/cm_s30-*
. build/envsetup.sh
lunch cm_s30-user
rm out/target/product/s30/system/build.prop
rm out/target/product/s30/recovery.img
cp device/vernee/s30/TFNomuS30TWRPrecovery.img out/target/product/s30/recovery.img
cp -rf zprojects/overlay/packages .
cp -rf zprojects/overlay/frameworks .
mka dist
rm out/target/product/s30/recovery.img
cp device/vernee/s30/TFNomuS30TWRPrecovery.img out/target/product/s30/recovery.img
#rm -r out/target/product/s30/ROM/
#mkdir out/target/product/s30/ROM
#cp out/target/product/s30/boot.img out/target/product/s30/ROM/
#cp out/target/product/s30/cache.img out/target/product/s30/ROM/
#cp out/target/product/s30/custom.img out/target/product/s30/ROM/
#cp out/target/product/s30/lk.bin out/target/product/s30/ROM/
#cp out/target/product/s30/logo.bin out/target/product/s30/ROM/
#cp out/target/product/s30/MT6797_Android_scatter.txt out/target/product/s30/ROM/
#cp out/target/product/s30/preloader_s30.bin out/target/product/s30/ROM/
#cp out/target/product/s30/recovery.img out/target/product/s30/ROM/
#cp out/target/product/s30/secro.img out/target/product/s30/ROM/
#cp out/target/product/s30/system.img out/target/product/s30/ROM/
#cp out/target/product/s30/trustzone.bin out/target/product/s30/ROM/
#cp out/target/product/s30/userdata.img out/target/product/s30/ROM/
#cp out/target/product/s30/md1rom.img out/target/product/s30/ROM/
#cp out/target/product/s30/md1dsp.img out/target/product/s30/ROM/
#cp out/target/product/s30/md1arm7.img out/target/product/s30/ROM/
#cp out/target/product/s30/md3rom.img out/target/product/s30/ROM/
#cp out/target/product/s30/tinysys-scp.bin out/target/product/s30/ROM/
#cp out/target/product/s30/tinysys-scp.bin out/target/product/s30/ROM/
#ahora=$(date +%d%m%Y%I%M%S)
#cd out/target/product/s30/
##tar -zcvf verneeApolloLite_$ahora.tar.gz ROM/
#zip -r verneeApolloLite_$ahora.zip ROM/
#mv verneeApolloLite_$ahora.zip ../../../../
#cd ../../../../
#echo 'Enviando a GDRIVE'
#drive upload verneeApolloLite_$ahora.zip
