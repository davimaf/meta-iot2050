#
# Copyright (c) Siemens AG, 2019-2020
#
# Authors:
#  Le Jin <le.jin@siemens.com>
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.  See
# COPYING.MIT file in the top-level directory.
#

require recipes-bsp/u-boot/u-boot-custom.inc

SRC_URI += " \
    file://${U_BOOT_RULES} \
    file://keys \
    file://prebuild \
    "

U_BOOT_BIN = "u-boot.itb"

S = "${WORKDIR}/git"

SPI_FLASH_IMG = "iot2050-image-boot-${IOT2050_VARIANT}.bin"
SPI_FLASH_SIZE_KB = "8192"

# Build environment
BUILD_DEPENDS =. "openssl, libssl-dev:native, libssl-dev:arm64,"

do_prepare_build_append() {
    ln -sf ../keys ${S}
    cp ${WORKDIR}/${U_BOOT_RULES} ${S}/debian/rules
    echo "${SPI_FLASH_IMG} /usr/lib/u-boot/${MACHINE}" > \
        ${S}/debian/u-boot-${MACHINE}.install
    echo -$(${ISAR_RELEASE_CMD}) > ${S}/.scmversion
}

do_build[dirs] += "${DEPLOY_DIR_IMAGE}"

dpkg_runbuild_prepend() {
    export IOT2050_VARIANT=${IOT2050_VARIANT}
    export SPI_FLASH_IMG=${SPI_FLASH_IMG}
    export SPI_FLASH_SIZE_KB=${SPI_FLASH_SIZE_KB}
}

do_deploy() {
    cp -f "${S}/${SPI_FLASH_IMG}" "${DEPLOY_DIR_IMAGE}"
}

addtask deploy after do_dpkg_build before do_build
do_deploy[dirs] = "${DEPLOY_DIR_IMAGE}"
