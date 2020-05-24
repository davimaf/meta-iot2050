#
# Copyright (c) Siemens AG, 2019-2020
#
# Authors:
#  Gao Nian <nian.gao@siemens.com>
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.  See
# COPYING.MIT file in the top-level directory.
#

inherit dpkg

DESCRIPTION = "Tool to switch between the uart0 modes"
MAINTAINER = "nian.gao@siemens.com"

SRC_URI = "file://switchserialmode"

S = "${WORKDIR}/switchserialmode"

DEBIAN_BUILD_DEPENDS = "cmake, libusb-1.0-0-dev"

do_prepare_build[cleandirs] += "${S}/debian"

do_prepare_build() {
    deb_debianize
}
