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
    git://github.com/OP-TEE/optee_os.git;protocol=https  \
    file://0001-ta-Add-missing-default-of-user-ta-version.patch \
    file://0002-plat-k3-Make-UART-number-configurable-via-CFG_CONSOL.patch \
    file://rules"
SRCREV = "af141c61fe7a2430f3b4bb89661d8414117013b3"

DEBIAN_BUILD_DEPENDS = " \
    python3-crypto:native, \
    python3-pycryptodome:native, \
    python3-pyelftools"

S = "${WORKDIR}/git"

do_prepare_build[cleandirs] += "${S}/debian"
do_prepare_build() {
    deb_debianize

    echo "out/arm-plat-k3/core/tee-pager_v2.bin /usr/lib/op-tee/iot2050/" > ${S}/debian/install
}
