#include "DynamicLine.h"
#include <Ogre.h>
#include <cassert>
#include <cmath>

using namespace Ogre;

namespace Ogre4j
{

enum {
	POSITION_BINDING,
	TEXCOORD_BINDING
};

DynamicLine::DynamicLine()
{
#ifdef _DEBUG
	std::cout << "DynamicLine::DynamicLine" << std::endl;
#endif
	initialize( getOperationType(),false );
	setMaterial( "BaseWhiteNoLighting" );
	mDirty = true;
}

DynamicLine::~DynamicLine()
{
#ifdef _DEBUG
	std::cout << "DynamicLine::~DynamicLine" << std::endl;
#endif

}

RenderOperation::OperationType DynamicLine::getOperationType() const
{
#ifdef _DEBUG
	std::cout << "DynamicLine::getOperationType" << std::endl;
#endif
	return Ogre::RenderOperation::OT_LINE_LIST;
}

void DynamicLine::addPoint(const Vector3 &p)
{
#ifdef _DEBUG
	std::cout << "DynamicLine::addPoint" << std::endl;
#endif
	mPoints.push_back(p);
	mDirty = true;
}
void DynamicLine::addPoint(Real x, Real y, Real z)
{
#ifdef _DEBUG
	std::cout << "DynamicLine::addPoint" << std::endl;
#endif
	mPoints.push_back(Vector3(x,y,z));
	mDirty = true;
}
const Vector3& DynamicLine::getPoint(unsigned short index) const
{
#ifdef _DEBUG
	std::cout << "DynamicLine::getPoint" << std::endl;
#endif
	assert(index < mPoints.size() && "Point index is out of bounds!!");
	return mPoints[index];
}
unsigned short DynamicLine::getNumPoints(void) const
{
#ifdef _DEBUG
	std::cout << "DynamicLine::getNumPoints" << std::endl;
#endif
	return (unsigned short)mPoints.size();
}
void DynamicLine::setPoint(unsigned short index, const Vector3 &value)
{
#ifdef _DEBUG
	std::cout << "DynamicLine::setPoint" << std::endl;
#endif
	assert(index < mPoints.size() && "Point index is out of bounds!!");

	mPoints[index] = value;
	mDirty = true;
}
void DynamicLine::clear()
{
#ifdef _DEBUG
	std::cout << "DynamicLine::clear" << std::endl;
#endif
	mPoints.clear();
	mDirty = true;
}

void DynamicLine::update()
{
#ifdef _DEBUG
	std::cout << "DynamicLine::update" << std::endl;
#endif
	if (mDirty) fillHardwareBuffers();
}

void DynamicLine::createVertexDeclaration()
{
#ifdef _DEBUG
	std::cout << "DynamicLine::createVertexDeclaration" << std::endl;
#endif
	VertexDeclaration *decl = mRenderOp.vertexData->vertexDeclaration;
	decl->addElement(POSITION_BINDING, 0, VET_FLOAT3, VES_POSITION);
}

void DynamicLine::fillHardwareBuffers()
{
#ifdef _DEBUG
	std::cout << "DynamicLine::fillHardwareBuffers" << std::endl;
#endif
	int size = (int)mPoints.size();

	prepareHardwareBuffers(size,0);

	if (!size) { 
		mBox.setExtents(Vector3::ZERO,Vector3::ZERO);
		mDirty=false;
		return;
	}

	Vector3 vaabMin = mPoints[0];
	Vector3 vaabMax = mPoints[0];

	HardwareVertexBufferSharedPtr vbuf =
		mRenderOp.vertexData->vertexBufferBinding->getBuffer(0);

	Real *prPos = static_cast<Real*>(vbuf->lock(HardwareBuffer::HBL_DISCARD));
	{
		for(int i = 0; i < size; i++)
		{
			*prPos++ = mPoints[i].x;
			*prPos++ = mPoints[i].y;
			*prPos++ = mPoints[i].z;

			if(mPoints[i].x < vaabMin.x)
				vaabMin.x = mPoints[i].x;
			if(mPoints[i].y < vaabMin.y)
				vaabMin.y = mPoints[i].y;
			if(mPoints[i].z < vaabMin.z)
				vaabMin.z = mPoints[i].z;

			if(mPoints[i].x > vaabMax.x)
				vaabMax.x = mPoints[i].x;
			if(mPoints[i].y > vaabMax.y)
				vaabMax.y = mPoints[i].y;
			if(mPoints[i].z > vaabMax.z)
				vaabMax.z = mPoints[i].z;
		}
	}
	vbuf->unlock();

	mBox.setExtents(vaabMin, vaabMax);

	mDirty = false;
}

}
