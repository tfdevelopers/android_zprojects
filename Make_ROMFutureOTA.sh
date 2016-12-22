#!/bin/bash
rm -r out/dist
rm -r out/target/product/k11ta_a/cm_k11ta_a-*
. build/envsetup.sh
lunch cm_k11ta_a-user
rm out/target/product/k11ta_a/system/build.prop
rm out/target/product/k11ta_a/recovery.img
cp device/ulefone/k11ta_a/TFFutureTWRPrecovery.img out/target/product/k11ta_a/recovery.img
cp -rf zprojects/overlay/packages .
cp -rf zprojects/overlay/frameworks .
mka dist
rm out/target/product/k11ta_a/recovery.img
cp device/ulefone/k11ta_a/TFFutureTWRPrecovery.img out/target/product/k11ta_a/recovery.img
#rm -r out/target/product/k11ta_a/ROM/
#mkdir out/target/product/k11ta_a/ROM
#cp out/target/product/k11ta_a/boot.img out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/cache.img out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/custom.img out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/lk.bin out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/logo.bin out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/MT6797_Android_scatter.txt out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/preloader_k11ta_a.bin out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/recovery.img out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/secro.img out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/system.img out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/trustzone.bin out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/userdata.img out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/md1rom.img out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/md1dsp.img out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/md1arm7.img out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/md3rom.img out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/tinysys-scp.bin out/target/product/k11ta_a/ROM/
#cp out/target/product/k11ta_a/tinysys-scp.bin out/target/product/k11ta_a/ROM/
#ahora=$(date +%d%m%Y%I%M%S)
#cd out/target/product/k11ta_a/
##tar -zcvf verneeApolloLite_$ahora.tar.gz ROM/
#zip -r verneeApolloLite_$ahora.zip ROM/
#mv verneeApolloLite_$ahora.zip ../../../../
#cd ../../../../
#echo 'Enviando a GDRIVE'
#drive upload verneeApolloLite_$ahora.zip
