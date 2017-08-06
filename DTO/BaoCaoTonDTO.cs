using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DTO
{
    class BaoCaoTonDTO
    {
        private string _MaBaoCaoTon;
        private string _MaHang;
        private int _Thang;

        public string MaBaoCaoHoaDon
        {
            get
            {
                return _MaBaoCaoTon;
            }

            set
            {
                _MaBaoCaoTon = value;
            }
        }

        public string MaHoaDon
        {
            get
            {
                return _MaHang;
            }

            set
            {
                _MaHang = value;
            }
        }

        public int Thang
        {
            get
            {
                return _Thang;
            }

            set
            {
                _Thang = value;
            }
        }

        private void New()
        {
            _MaBaoCaoTon= null;
            _MaHang = null;

        }
    }
}
