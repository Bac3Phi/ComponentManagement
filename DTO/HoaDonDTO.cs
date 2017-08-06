using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DTO
{
    class HoaDonDTO
    {
        private string _MaHoaDon;
        private string _MaKhachHang;
        private DateTime _NgayLap;
        private string _MaNhanVien;
        private int _SoTienThanhToan;
        private DateTime ThoiGianBaoHanh;

        private void New()
            {
            _MaHoaDon = null;
            _MaKhachHang = null;
            _NgayLap = DateTime.Now;
            _MaNhanVien = null;
            _SoTienThanhToan = 0;
            }
        public string MaHoaDon
        {
            get
            {
                return _MaHoaDon;
            }

            set
            {
                _MaHoaDon = value;
            }
        }

        public string MaKhachHang
        {
            get
            {
                return _MaKhachHang;
            }

            set
            {
                _MaKhachHang = value;
            }
        }

        public DateTime NgayLap
        {
            get
            {
                return _NgayLap;
            }

            set
            {
                _NgayLap = value;
            }
        }

        public string MaNhanVien
        {
            get
            {
                return _MaNhanVien;
            }

            set
            {
                _MaNhanVien = value;
            }
        }

        public int SoTienThanhToan
        {
            get
            {
                return _SoTienThanhToan;
            }

            set
            {
                _SoTienThanhToan = value;
            }
        }

        public DateTime ThoiGianBaoHanh1
        {
            get
            {
                return ThoiGianBaoHanh;
            }

            set
            {
                ThoiGianBaoHanh = value;
            }
        }
    }
}
