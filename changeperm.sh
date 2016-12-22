#!/bin/bash
if  grep 'ro.product.model=k15tb_a' out/target/product/k15tb_a/system/build.prop ; then

sed -i s/test-keys/release-keys/g out/target/product/k15tb_a/system/build.prop
#sed -i s/userdebug/user/g out/target/product/k15tb_a/system/build.prop
cp device/vernee/k15tb_a/UPDATE-SuperSU-v2.76systemless.zip out/target/product/k15tb_a/system/UPDATE-SuperSU-v2.76systemless.zip
rm out/target/product/k15tb_a/system/unroot.zip
rm device/vernee/k15tb_a/unroot/boot.img
cp out/target/product/k15tb_a/boot.img device/vernee/k15tb_a/unroot/boot.img
cd device/vernee/k15tb_a/unroot/
zip -6 -r ../../../../out/target/product/k15tb_a/system/unroot.zip .
cd ../../../../

fi

if  grep 'ro.product.model=S30' out/target/product/s30/system/build.prop ; then

sed -i s/test-keys/release-keys/g out/target/product/s30/system/build.prop
#sed -i s/userdebug/user/g out/target/product/s30/system/build.prop
cp device/nomu/s30/UPDATE-SuperSU-v2.76systemless.zip out/target/product/s30/system/UPDATE-SuperSU-v2.76systemless.zip
rm out/target/product/s30/system/unroot.zip
rm device/nomu/s30/unroot/boot.img
cp out/target/product/s30/boot.img device/nomu/s30/unroot/boot.img
cd device/nomu/s30/unroot/
zip -6 -r ../../../../out/target/product/s30/system/unroot.zip .
cd ../../../../

fi

if  grep 'ro.product.model=k11ta_a' out/target/product/k11ta_a/system/build.prop ; then

sed -i s/test-keys/release-keys/g out/target/product/k11ta_a/system/build.prop
#sed -i s/userdebug/user/g out/target/product/s30/system/build.prop
cp device/ulefone/k11ta_a/UPDATE-SuperSU-v2.76systemless.zip out/target/product/k11ta_a/system/UPDATE-SuperSU-v2.76systemless.zip
rm out/target/product/k11ta_a/system/unroot.zip
rm device/ulefone/k11ta_a/unroot/boot.img
cp out/target/product/k11ta_a/boot.img device/ulefone/k11ta_a/unroot/boot.img
cd device/ulefone/k11ta_a/unroot/
zip -6 -r ../../../../out/target/product/k11ta_a/system/unroot.zip .
cd ../../../../

fi
