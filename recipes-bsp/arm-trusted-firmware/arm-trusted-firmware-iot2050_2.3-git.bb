#
# Copyright (c) Siemens AG, 2020
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.  See
# COPYING.MIT file in the top-level directory.
#

inherit dpkg

SRC_URI = " \
    git://github.com/ARM-software/arm-trusted-firmware.git;protocol=https \
    file://0001-ti-k3-common-Implement-stub-system_off.patch \
    file://0002-Add-infrastructure-for-selecting-output-UART.patch \
    file://0003-ti-k3-common-Make-UART-number-configurable.patch \
    file://rules"
SRCREV = "f1a1653ce17861441383ae58a3df929cb521c9d8"

S = "${WORKDIR}/git"

do_prepare_build[cleandirs] += "${S}/debian"
do_prepare_build() {
    deb_debianize

    echo "build/k3/generic/release/bl31.bin /usr/lib/arm-trusted-firmware/iot2050/" > ${S}/debian/install
}
