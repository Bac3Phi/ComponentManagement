using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DTO
{
    class DatHangDTO
    {
        private string _MaDatHang;
        private string _MaNhanVien;
        private string _MaNCC;
        private DateTime _NgayLap;

        private void New()
        {
            _MaDatHang = null;
            _MaNhanVien = null;
            _MaNCC = null;
            _NgayLap = DateTime.Now;
          
        }
        public string MaDatHang
        {
            get
            {
                return _MaDatHang;
            }

            set
            {
                _MaDatHang = value;
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

        public string MaNCC
        {
            get
            {
                return _MaNCC;
            }

            set
            {
                _MaNCC = value;
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
    }
}
